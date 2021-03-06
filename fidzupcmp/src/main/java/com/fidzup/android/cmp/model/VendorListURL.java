package com.fidzup.android.cmp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fidzup.android.cmp.Constants;

/**
 * Reprensents the URL to a vendor list.
 */

public class VendorListURL {

    // The actual URL of the vendor list.
    @NonNull
    private String URL;

    // The actual URL of the localized vendor list.
    @Nullable
    private String localizedURL;

    // The language the URL is localized against
    @Nullable
    private Language language;

    /**
     * Initialize a VendorListURL object that represents the latest vendor list.
     *
     * @param language The language of the user if a localized URL has to be used.
     */
    public VendorListURL(@Nullable Language language) {
        URL = Constants.VendorList.DefaultEndPoint;
        this.language = language;

        if (this.language != null) {
            localizedURL = Constants.VendorList.DefaultLocalizedEndPoint.replace("{language}", this.language.toString());
        }
    }

    /**
     * Initialize a VendorListURL object that represents the vendor list for a given version.
     *
     * @param version  The vendor list version that should be fetched.
     * @param language The language of the user if a localized URL has to be used.
     */
    public VendorListURL(int version, @Nullable Language language) throws IllegalArgumentException {
        this.language = language;
        if (version < 1) {
            Log.e("FidzupCMP", "VendorListURL can not be configured. The version must be greater than 0.");
            throw new IllegalArgumentException("Version can not be lower than 1");
        }

        URL = Constants.VendorList.VersionedEndPoint.replace("{version}", "" + version);

        if (this.language != null) {
            localizedURL = Constants.VendorList.VersionedLocalizedEndPoint.replace("{language}", this.language.toString())
                    .replace("{version}", "" + version);
        }
    }

    /**
     * @return The URL of the vendor list.
     */
    @NonNull
    public String getURL() {
        return URL;
    }

    /**
     * @return The localized URL of the vendor list.
     */
    @Nullable
    public String getLocalizedURL() {
        return localizedURL;
    }

    /**
     * @return the language used to localize this URL (or null if not defined)
     */
    @Nullable
    public Language getLanguage() {
        return language;
    }
}
