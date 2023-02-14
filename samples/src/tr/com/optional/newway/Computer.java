package tr.com.optional.newway;

import java.util.Optional;

public class Computer {

    private Optional<SoundCard> soundCard;

    public static Computer build() {
        final Computer computer = new Computer();
        computer.setSoundCard(SoundCard.build());
        return computer;
    }

    public static Computer buildWithoutSoundCar() {
        final Computer computer = new Computer();
        computer.setSoundCard(null);
        return computer;
    }

    public Optional<SoundCard> getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(final SoundCard soundCard) {
        this.soundCard = Optional.ofNullable(soundCard);
    }
}
