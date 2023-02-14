package tr.com.optional.newway;

import java.util.Optional;

public class SoundCard {

    private Optional<USB> usb;

    public static SoundCard build() {
        final SoundCard soundCard = new SoundCard();
        soundCard.setUsb(new USB());
        return soundCard;
    }

    public Optional<USB> getUsb() {
        return usb;
    }

    public void setUsb(final USB usb) {
        this.usb = Optional.of(usb);
    }
}
