package tr.com.collections.set;

/**
 * @author serhat.ozkan
 */

import java.util.EnumSet;
import java.util.stream.Collectors;

/**
 *
 * Notes From: Oracle official website and Baeldung
 *
 * Enum sets are represented internally as bit vectors.
 * This representation is extremely compact and efficient.
 * The space and time performance of this class should be good enough to allow its use as a high-quality,
 * typesafe alternative to traditional int-based "bit flags." Even bulk operations (such as containsAll and retainAll) should run very
 * quickly if their argument is also an enum set.
 *
 * **The elements are stored following the order in which they are declared in the enum
 *
 * ** It uses a fail-safe iterator that works on a copy, so it won't throw a ConcurrentModificationException
 * if the collection is modified when iterating over it
 *
 * . The JDK provides 2 different implementations – are package-private and backed by a bit vector:*
 * 1-) RegularEnumSet and
 * 2-) JumboEnumSet
 * RegularEnumSet uses a single long to represent the bit vector. Each bit of the long element represents a value of the enum.
 * The i-th value of the enum will be stored in the i-th bit, so it's quite easy to know whether a value is present or not.
 * Since long is a 64-bit data type, this implementation can store up to 64 elements.
 *
 * JumboEnumSet uses array to store values
 *
 * ***** Main benefit it uses bitwise operators so its memory and cpu efficient
 * ***** It is recomended to use when-ever possible
 */
public class EnumSetExample {

    public static void main(final String[] args) {
        final EnumSet<MilitaryAircraftCompany> turkishCompanies = EnumSet.of(MilitaryAircraftCompany.TUSAS, MilitaryAircraftCompany.BAYRAKTAR,
                MilitaryAircraftCompany.ALP_HAVACILIK);

        final EnumSet<MilitaryAircraftCompany> foreignCompanies = EnumSet.complementOf(turkishCompanies);

        final String turkishCompaniesText = turkishCompanies.stream().map(MilitaryAircraftCompany::toString).collect(Collectors.joining(", "));
        // *** Enum values are printed same with the order which they have defined at Enum.
        System.out.println("Turkish Companies: " + turkishCompaniesText);

        final String foreignCompaniesText = foreignCompanies.stream().map(MilitaryAircraftCompany::toString).collect(Collectors.joining(", "));
        // *** Enum values are printed same with the order which they have declared at Enum.
        System.out.println("Foreign Companies: " + foreignCompaniesText);

        for (MilitaryAircraftCompany foreignCompany : foreignCompanies) {
            // With other non-thread safe sets we would have received concurrent modification exception.
            // Enum set is fail-safe
            if ("RUSSIA".equalsIgnoreCase(foreignCompany.nation)) {
                foreignCompanies.remove(foreignCompany);
            }
        }
    }

    private enum MilitaryAircraftCompany {
        BAYRAKTAR("TÜRKİYE"),
        TUSAS("TÜRKİYE"),
        ALP_HAVACILIK("TÜRKİYE"),
        SAAB("SWEDEN"),
        DASSAULT("FRANCE"),
        BRITISH_AEROSPACE("GB"),
        LOCKHEED_MARTIN("USA"),
        NORTHROP_GRUMMAN("USA"),
        BOEING("USA"),
        IAI("ISRAEL"),
        KAI("REPUBLIC OF SOUTH KOREA"),
        SUKHOI_DESIGN_BUREAU("RUSSIA"),
        MIKOYAN_GUREVICH("RUSSIA"),
        TUPOLEV("RUSSIA");


        final String nation;
        MilitaryAircraftCompany(final String nation) {
            this.nation = nation;

        }
    }

}
