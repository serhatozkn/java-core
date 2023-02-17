package tr.com.optional;

import tr.com.optional.newway.Computer;
import tr.com.optional.newway.SoundCard;
import tr.com.optional.newway.USB;
import tr.com.optional.oldway.ComputerOldWay;
import tr.com.optional.oldway.SoundCarOldWay;
import tr.com.optional.oldway.USBOldWay;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ComputerExample {

    public static void main(final String[] args) {

        final ComputerOldWay computerOldWay = ComputerOldWay.build();

        // Traditional way with null checks
        if (computerOldWay != null) {
            final SoundCarOldWay soundCard = computerOldWay.getSoundCard();
            if (soundCard != null) {
                final USBOldWay usb = soundCard.getUsb();
                if (usb != null) {
                    System.out.println("Version : " + usb.getVersion());
                }
            }
        }

        // Newer way with Optional
        Optional<Computer> computer = Optional.of(Computer.build());

        // Note
        // Use map if the function returns the object you need or flatMap if the function returns an Optional. For example:

        final String version = computer.flatMap(Computer::getSoundCard)
                .flatMap(SoundCard::getUsb)
                .map(USB::getVersion)
                .orElse("Unknown");

        System.out.println("Verison : " + version);

        // Let's see what happens with empty
        final Optional<Computer> computerWithoutSound = Optional.of(Computer.buildWithoutSoundCar());
        final String versionWithoutSoundCard = computerWithoutSound.flatMap(Computer::getSoundCard)
                .flatMap(SoundCard::getUsb)
                .map(USB::getVersion)
                .orElse("Unknown");
        System.out.println("Version: " + versionWithoutSoundCard);

/////////////////////////////////////////////////////////////////////////////////////

        // Instead of doing below
        if (computerOldWay != null) {
            System.out.println(computerOldWay);
        }
        // We can do this
        computer.ifPresent(System.out::println);
/////////////////////////////////////////////////////////////////////////////////////

        computer = Optional.ofNullable(null);
        try {
            computer.get();
        }catch (NoSuchElementException ex) {
            System.out.println("Calling get on Optional.ofNullable(null) generates NoSuchElementException");
        }

        computer = Optional.empty();
        try {
            computer.get();
        }catch (NoSuchElementException ex) {
            System.out.println("Calling get on Optional.empty() generates NoSuchElementException");
        }

/////////////////////////////////////////////////////////////////////////////////////////

        // Instead of
        // Computer computer2 = comp != null ? comp : new Computer();
        // We can do

        Computer computerRaw = computer.orElse(new Computer());
        if (computerRaw == null) {
            throw new AssertionError("Worked at unexpected way");
        }
    }
}
