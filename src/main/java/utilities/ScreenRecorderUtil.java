package utilities;

import org.monte.media.Format;
import org.monte.media.FormatKeys.*;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import static helpers.PathHelper.projectPath;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static utilities.DateTime.getCurrentDateTime;

public class ScreenRecorderUtil extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    public String name;
    public static String VIDEO_FILES_FOLDER = projectPath + "videoRecording" + File.separator;
    public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                              Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File(movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }
    public static void startRecord(String recordedVideoName) throws Exception {
        File file = new File("./test-recordings/");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();
        screenRecorder = new ScreenRecorderUtil(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, recordedVideoName);
        screenRecorder.start();
    }
    public static void stopRecord() {
        try {
            screenRecorder.stop();
        } catch (Exception e) {

        }
    }
    public static void deleteRecords() {
        File directory = new File("./test-recordings/");
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
    }
    public static String saveVideo(String video, ITestResult result) {
        byte[] decoVideo = Base64.getMimeDecoder().decode(video);
        String name = result.getName() + "_" + getCurrentDateTime("dd-MM-yyyy") +  "_video.mp4";
        try {
            Path testFileVideo = Paths.get(projectPath + "videoRecording/" + name);
            System.out.println("file video" + testFileVideo);
            Files.write(testFileVideo, decoVideo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

}
