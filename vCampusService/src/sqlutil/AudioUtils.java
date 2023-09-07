package sqlutil;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioUtils {

    private static AudioFormat af;
    private static Info info;
    private static TargetDataLine td;
    private static Info dataLineInfo;
    private static SourceDataLine sd;

    /**
     * ��ȡ��Ƶ������(��ʰ����)
     *
     * @return TargetDataLine
     * @throws LineUnavailableException
     */
    public static TargetDataLine getTargetDataLine() throws LineUnavailableException {
        if (td != null) {
            return td;
        } else {
            // 1.��ȡ��Ƶ������
            // afΪAudioFormatҲ������Ƶ��ʽ
            af = getAudioFormat();
            info = new DataLine.Info(TargetDataLine.class, af);
            // �����tdʵ������
            td = (TargetDataLine) (AudioSystem.getLine(info));
            // �򿪾���ָ����ʽ���У�������ʹ�л�����������ϵͳ��Դ����ÿɲ�����
            td.open(af);
            // ����ĳһ������ִ������ I/O
            td.start();
            return td;
        }

    }
    /**
     * ��ȡ����� д�����ݻ��Զ�����
     *
     * @return SourceDataLine
     * @throws LineUnavailableException
     */
    public static SourceDataLine getSourceDataLine() throws LineUnavailableException {
        if (sd != null) {
            return sd;
        } else {
            // 2.����Ƶ����ȡ����
            dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
            sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

            sd.open(af);
            // ����ĳһ������ִ������ I/O
            sd.start();

            return sd;
        }
    }

    /**
     * ����AudioFormat�Ĳ���
     *
     * @return AudioFormat
     */
    public static AudioFormat getAudioFormat() {
        return new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, 8000f, 8, 1, 1, AudioSystem.NOT_SPECIFIED, false);
    }

    /**
     * �ر���Դ
     */
    public static void close() {
        if (td != null) {
            td.close();
        }
        if (sd != null) {
            sd.close();
        }

    }
}