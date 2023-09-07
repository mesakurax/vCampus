import javax.sound.sampled.*;
import javax.sound.sampled.Line.Info;

public class AudioFormatUtils {

    public static void main(String[] args) {
        // 获取所有音频设备
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();

        // 遍历音频设备
        for (Mixer.Info mixerInfo : mixerInfos) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);

            // 获取目标数据行信息
            Line.Info[] targetLineInfos = mixer.getTargetLineInfo();
            for (Line.Info lineInfo : targetLineInfos) {
                if (lineInfo instanceof DataLine.Info) {
                    DataLine.Info dataLineInfo = (DataLine.Info) lineInfo;
                    if (dataLineInfo.isFormatSupported(getAudioFormat())) {
                        System.out.println("Supported target line format: " + dataLineInfo.getFormats()[0]);
                    }
                }
            }

            // 获取源数据行信息
            Line.Info[] sourceLineInfos = mixer.getSourceLineInfo();
            for (Line.Info lineInfo : sourceLineInfos) {
                if (lineInfo instanceof DataLine.Info) {
                    DataLine.Info dataLineInfo = (DataLine.Info) lineInfo;
                    if (dataLineInfo.isFormatSupported(getAudioFormat())) {
                        System.out.println("Supported source line format: " + dataLineInfo.getFormats()[0]);
                    }
                }
            }
        }
    }

    // 设置所需的音频格式
    public static AudioFormat getAudioFormat() {
        AudioFormat format = new AudioFormat(8000f, 16, 1, true, false);
        return format;
    }
}