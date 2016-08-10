package dextrous.app.hack.ai.cedric.constant;


import java.util.Arrays;
import java.util.List;

public class CedricConstants {
    public static final String SERVER_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final String LOCAL_SERVER_URL = "http://10.0.2.2:8090/api/";

    public static final String INTENT_PARAM_IMAGE_FILE_PATH = "imageFilePath";
    public static final String INTENT_PARAM_DESCRIPTION_RESULT = "imageDescriptionResult";

    public static final String APP_ROOT_DIRECTORY = "Cedric";
    public static final String APP_IMAGES_DIRECTORY = "Images";

    public static final String FILE_NAME_SYNTAX = "IMG_%s.jpg";

    public static final String DATE_FORMAT_IN_FILE_NAME = "yyyyMMdd_HHmmss";

    public static final String PARENT_DIR = "..";

    public static final String DELIMITER_DOT = ".";

    public static final String SUFFIX_COMPRESSED = "_compressed";
    public static final int COMPRESSION_QUALITY = 100;

    public static final List<String> VALID_IMAGE_FILE_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png",".gif","tiff");

    public static final String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

    public static final String LOG_TAG_HTTP_RESPONSE = "HTTP_RESPONSE";
    public static final String LOG_TAG_HTTP_ERROR = "HTTP_ERROR";

    public static final int DEFAULT_READ_TIMEOUT = 10; // => minutes
    public static final int DEFAULT_CONNECT_TIMEOUT = 60; // => seconds
    public static final int BITMAP_WIDTH_THRESHOLD = 512;

    public static final long MAX_FILE_SIZE_LIMIT = 4 * 1024 * 1024;// <== 4MB

    public static final String MSG_PROGRESS_DIALOG_TITLE  = "Loading...";
    public static final String MSG_PROGRESS_DIALOG_MESSAGE  = "Please wait..";
}
