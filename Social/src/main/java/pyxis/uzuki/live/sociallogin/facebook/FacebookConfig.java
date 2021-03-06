package pyxis.uzuki.live.sociallogin.facebook;

import java.util.ArrayList;

import pyxis.uzuki.live.sociallogin.impl.SocialConfig;

/**
 * SocialLogin
 * Class: FacebookConfig
 * Created by Pyxis on 2017-10-27.
 * <p>
 * Description:
 */

public class FacebookConfig extends SocialConfig {
    private ArrayList<String> requestOptions;
    private boolean requireWritePermissions;
    private String applicationId;

    private FacebookConfig(ArrayList<String> requestOptions, boolean requireWritePermissions, String applicationId) {
        this.requestOptions = requestOptions;
        this.requireWritePermissions = requireWritePermissions;
        this.applicationId = applicationId;
    }

    public ArrayList<String> getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(ArrayList<String> requestOptions) {
        this.requestOptions = requestOptions;
    }

    public boolean isRequireWritePermissions() {
        return requireWritePermissions;
    }

    public void setRequireWritePermissions(boolean requireWritePermissions) {
        this.requireWritePermissions = requireWritePermissions;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public static class Builder {
        private boolean isRequireEmail = false;
        private boolean requireWritePermissions = false;
        private String applicationId;

        public Builder setRequireEmail() {
            isRequireEmail = true;
            return this;
        }

        public Builder setRequireWritePermission() {
            requireWritePermissions = true;
            return this;
        }

        public Builder setApplicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public FacebookConfig build() {
            ArrayList<String> requestOptions = new ArrayList<>();
            if (isRequireEmail) {
                requestOptions.add("email");
            }

            requestOptions.add("public_profile");
            requestOptions.add("user_friends");
            return new FacebookConfig(requestOptions, requireWritePermissions, applicationId);
        }
    }
}
