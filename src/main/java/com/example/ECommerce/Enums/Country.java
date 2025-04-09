package com.example.ECommerce.Enums;

public enum Country {
    // Example countries with their ISO codes
    UNITED_STATES("US"),
    CANADA("CA"),
    UNITED_KINGDOM("GB"),
    AUSTRALIA("AU"),
    INDIA("IN"),
    CHINA("CN"),
    BRAZIL("BR"),
    SOUTH_AFRICA("ZA"),
    GERMANY("DE"),
    FRANCE("FR"),
    JAPAN("JP"),
    RUSSIA("RU"),
    ITALY("IT"),
    MEXICO("MX"),
    NIGERIA("NG"),
    SOUTH_KOREA("KR"),
    SAUDI_ARABIA("SA"),
    ARGENTINA("AR"),
    INDONESIA("ID"),
    TURKEY("TR"),
    EGYPT("EG"),
    SOUTH_KOREAN("KR"),
    KUWAIT("KW"),
    QATAR("QA"),
    MALAYSIA("MY"),
    SINGAPORE("SG"),
    THAILAND("TH"),
    VENEZUELA("VE"),
    CHILE("CL"),
    PERU("PE"),
    NETHERLANDS("NL"),
    SPAIN("ES"),
    SWITZERLAND("CH"),
    UNITED_ARAB_EMIRATES("AE"),
    PAKISTAN("PK"),
    NEW_ZEALAND("NZ"),
    HONG_KONG("HK"),
    MALDIVES("MV"),
    SRI_LANKA("LK"),
    BAHRAIN("BH"),
    BANGLADESH("BD"),
    VIETNAM("VN"),
    SWEDEN("SE"),
    NORWAY("NO"),
    DENMARK("DK"),
    IRELAND("IE"),
    POLAND("PL"),
    CZECH_REPUBLIC("CZ"),
    HUNGARY("HU"),
    ROMANIA("RO"),
    GREECE("GR"),
    BULGARIA("BG"),
    CROATIA("HR"),
    SERBIA("RS"),
    MONTENEGRO("ME"),
    ALBANIA("AL"),
    KAZAKHSTAN("KZ"),
    UKRAINE("UA"),
    BELARUS("BY"),
    LITHUANIA("LT"),
    LATVIA("LV"),   ;

    private final String isoCode;

    Country(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public static Country fromIsoCode(String isoCode) {
        for (Country country : values()) {
            if (country.getIsoCode().equalsIgnoreCase(isoCode)) {
                return country;
            }
        }
        throw new IllegalArgumentException("No country found with ISO code Or not supported within this application: " + isoCode);
    }

    @Override
    public String toString() {
        return name() + " (" + isoCode + ")";
    }
}
