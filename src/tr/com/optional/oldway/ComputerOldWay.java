package tr.com.optional.oldway;

public class ComputerOldWay {

    private SoundCarOldWay soundCard;

    public static ComputerOldWay build() {
        final ComputerOldWay computerOldWay = new ComputerOldWay();
        computerOldWay.setSoundCard(SoundCarOldWay.build());
        return computerOldWay;
    }

    public SoundCarOldWay getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(SoundCarOldWay soundCard) {
        this.soundCard = soundCard;
    }
}
