package io.toolisticon.myspiservice.processor;


import io.toolisticon.aptk.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum MySpiServiceProcessorMessages implements ValidationMessage {

    // TODO: Replace this by your own error messages
    ERROR_COULD_NOT_CREATE_CONFIG("MySpiService_ERROR_001", "Could not create config ${0}"),
    ERROR_ATTRIBUTE_VALUE_MUST_BE_INTERFACE("MySpiService_ERROR_002", "Value must be an interface");


    /**
     * the message code.
     */
    private final String code;
    /**
     * the message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param code    the message code
     * @param message the message text
     */
        MySpiServiceProcessorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code of the message.
     *
     * @return the message code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the message text.
     *
     * @return the message text
     */
    public String getMessage() {
        return message;
    }


}
