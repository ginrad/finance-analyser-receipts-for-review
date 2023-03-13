package com.ginrad.finance.analyser.receipts.model;

public class MerchantStore {

    private String id;
    private Merchant merchant;
    private String country;
    private String address;
    private String openingHours;

    private MerchantStore(MerchantStoreBuilder builder) {
        this.id = builder.id;
        this.merchant = builder.merchant;
        this.country = builder.country;
        this.address = builder.address;
        this.openingHours = builder.openingHours;
    }

    public static class MerchantStoreBuilder {
        private String id;
        private final Merchant merchant;
        private final String country;
        private final String address;
        private String openingHours;

        // All mandatory parameters goes with this constructor
        public MerchantStoreBuilder(Merchant merchant, String country, String address) {
            this.merchant = merchant;
            this.country = country;
            this.address = address;
        }

        // Setters for optional parameters
        public MerchantStoreBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public MerchantStoreBuilder withOpeningHours(String openingHours) {
            this.openingHours = openingHours;
            return this;
        }

        // Prepares and returns a MerchantStore object
        public MerchantStore build() {
            return new MerchantStore(this);
        }
    }

    public String getId() { return id; }

    public Merchant getMerchant() {
        return merchant;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getOpeningHours() {
        return openingHours;
    }
}
