package tr.com.optional.oldway;

public class SoundCarOldWay {

    private USBOldWay usb;

    public static SoundCarOldWay build() {
        final SoundCarOldWay soundCarOldWay = new SoundCarOldWay();
        soundCarOldWay.setUsb(new USBOldWay());
        return soundCarOldWay;
    }

    public USBOldWay getUsb() {
        return usb;
    }

    public void setUsb(USBOldWay usb) {
        this.usb = usb;
    }
}