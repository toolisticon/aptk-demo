package io.toolisticon.myspiservice.processor;

import io.toolisticon.aptk.tools.AbstractAnnotationProcessor;
import io.toolisticon.aptk.tools.FilerUtils;
import io.toolisticon.aptk.tools.MessagerUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.generators.SimpleResourceWriter;
import io.toolisticon.aptk.tools.wrapper.TypeElementWrapper;
import io.toolisticon.myspiservice.api.MySpiService;
import io.toolisticon.spiap.api.SpiService;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Annotation Processor for {@link io.toolisticon.myspiservice.api.MySpiService}.
 * <p>
 * This demo processor does some validations and creates a class.
 */

@SpiService(Processor.class)
public class MySpiServiceProcessor extends AbstractAnnotationProcessor {

    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(MySpiService.class);

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    Map<String, Set<String>> spiServices = new HashMap<>();

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (!roundEnv.processingOver()) {
            // process Services annotation
            for (Element element : roundEnv.getElementsAnnotatedWith(MySpiService.class)) {

                TypeElementWrapper wrappedTypeElement = TypeElementWrapper.wrap((TypeElement) element);
                MySpiServiceWrapper annotation = MySpiServiceWrapper.wrap(wrappedTypeElement.unwrap());

                if (validateUsage(wrappedTypeElement, annotation)) {
                    processAnnotation(wrappedTypeElement, annotation);
                }

            }


        } else {

            for (Map.Entry<String, Set<String>> serviceEntry : spiServices.entrySet()) {
                createServiceConfig(serviceEntry.getKey(), serviceEntry.getValue());
            }

        }
        return false;
    }

    void processAnnotation(TypeElementWrapper wrappedTypeElement, MySpiServiceWrapper annotation) {

        spiServices.computeIfAbsent(annotation.valueAsFqn(), e -> new HashSet<>()).add(wrappedTypeElement.getQualifiedName());

    }

    boolean validateUsage(TypeElementWrapper wrappedTypeElement, MySpiServiceWrapper annotation) {


        // test if annotation attribute value is interface
        if (!annotation.valueAsTypeMirrorWrapper().isInterface()) {

            // write error compiler message pin it to annotations value attribute
            annotation.valueAsAttributeWrapper().compilerMessage().asError()
                    .write(MySpiServiceProcessorMessages.ERROR_ATTRIBUTE_VALUE_MUST_BE_INTERFACE);

            return false;

        }

        // Check if element is class, has public modifier and a public noarg constructor
        return wrappedTypeElement.validateWithFluentElementValidator()
                .is(AptkCoreMatchers.IS_CLASS)
                .applyValidator(AptkCoreMatchers.BY_MODIFIER).hasAllOf(Modifier.PUBLIC)
                .applyValidator(AptkCoreMatchers.HAS_PUBLIC_NOARG_CONSTRUCTOR)
                .applyValidator(AptkCoreMatchers.IS_ASSIGNABLE_TO_FQN).hasOneOf(annotation.valueAsFqn())
                .validateAndIssueMessages();

    }

    /**
     * Generates a spi config file.
     *
     * @param service                The TypeElement representing the annotated class
     * @param serviceImplementations The MySpiService annotation
     */
    private void createServiceConfig(String service, Set<String> serviceImplementations) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("serviceImplementations", serviceImplementations);

        // create the class
        String fileName = "META-INF/services/" + service;
        try {
            SimpleResourceWriter resourceWriter = FilerUtils.createResource(fileName);
            resourceWriter.writeTemplate("/MySpiService.tpl", model);
            resourceWriter.close();
        } catch (IOException e) {
            MessagerUtils.error(null, MySpiServiceProcessorMessages.ERROR_COULD_NOT_CREATE_CONFIG, fileName);
        }
    }

}
