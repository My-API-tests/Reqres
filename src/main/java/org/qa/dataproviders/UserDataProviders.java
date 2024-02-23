package org.qa.dataproviders;

import org.qa.jsondatatransformer.JSONDataTransformer;
import org.qa.support.DataProviderNames;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class UserDataProviders {

    @DataProvider(name = DataProviderNames.CORRECT)
    public Object[] correct() throws IOException {

        return JSONDataTransformer.getUserData(DataProviderNames.CORRECT);
    }

    @DataProvider(name = DataProviderNames.MISSING_NAME)
    public Object[] withoutName() {

        return JSONDataTransformer.getUserData(DataProviderNames.MISSING_NAME);
    }

    @DataProvider(name = DataProviderNames.MISSING_JOB)
    public Object[] withoutJob() {

        return JSONDataTransformer.getUserData(DataProviderNames.MISSING_JOB);
    }
}
