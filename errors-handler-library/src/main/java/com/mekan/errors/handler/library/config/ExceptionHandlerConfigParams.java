package com.mekan.errors.handler.library.config;

public class ExceptionHandlerConfigParams {
    public static class Core {
        public static final String NO_SUCH_ELEMENT_MESSAGE = "no.such.element.message";
        public static final String ILLEGAL_ARGUMENT_MESSAGE = "illegal.argument.message";

        public static final String NO_SUCH_ELEMENT_STATUS = "no.such.element.status";
        public static final String ILLEGAL_ARGUMENT_STATUS = "illegal.argument.status";
    }

    public static class Custom {
        public static final String FILE_NOT_READ_MESSAGE = "file.not.read.message";
        public static final String FILE_STORAGE_MESSAGE = "file.storage.message";
        public static final String ILLEGAL_ACCESS_MESSAGE = "illegal.access.message";
        public static final String RESOURCE_NOT_FOUND_MESSAGE = "resource.not.found.message";

        public static final String FILE_NOT_READ_STATUS = "file.not.read.status";
        public static final String FILE_STORAGE_STATUS = "file.storage.status";
        public static final String ILLEGAL_ACCESS_STATUS = "illegal.access.status";
        public static final String RESOURCE_NOT_FOUND_STATUS = "resource.not.found.status";
    }
}
