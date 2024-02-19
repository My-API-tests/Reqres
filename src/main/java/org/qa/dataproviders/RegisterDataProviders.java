package org.qa.dataproviders;

import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.utils.DataProviderNames;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class RegisterDataProviders {

    @DataProvider(name = DataProviderNames.CORRECT)
    public Object[] correct() throws IOException {

        return JSONDataTransformer.getUserData(DataProviderNames.CORRECT);
    }

    @DataProvider(name = DataProviderNames.USER_NOT_DEFINED)
    public Object[] userNotDefined() {

        return JSONDataTransformer.getUserData(DataProviderNames.USER_NOT_DEFINED);
    }

    @DataProvider(name = DataProviderNames.INCORRECT_EMAIL)
    public Object[] incorrectEmail() {

        return JSONDataTransformer.getUserData(DataProviderNames.INCORRECT_EMAIL);
    }

    @DataProvider(name = DataProviderNames.INCORRECT_PASSWORD)
    public Object[] incorrectPassword() {

        return JSONDataTransformer.getUserData(DataProviderNames.INCORRECT_PASSWORD);
    }

    @DataProvider(name = DataProviderNames.MISSING_EMAIL)
    public Object[] withoutName() {

        return JSONDataTransformer.getUserData(DataProviderNames.MISSING_EMAIL);
    }

    @DataProvider(name = DataProviderNames.MISSING_PASSWORD)
    public Object[] withoutJob() {

        return JSONDataTransformer.getUserData(DataProviderNames.MISSING_PASSWORD);
    }
}
