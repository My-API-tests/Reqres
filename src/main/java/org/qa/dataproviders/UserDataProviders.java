package org.qa.dataproviders;

import org.qa.modelsbuilder.ModelsBuilder;
import org.qa.utils.DataProviderNames;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class UserDataProviders {

    @DataProvider(name = DataProviderNames.CORRECT)
    public Object[] correct() throws IOException {

        return ModelsBuilder.getUserBodies(DataProviderNames.CORRECT);
    }

    @DataProvider(name = DataProviderNames.WITHOUT_NAME)
    public Object[] withoutName() throws IOException {

        return ModelsBuilder.getUserBodies(DataProviderNames.WITHOUT_NAME);
    }

    @DataProvider(name = DataProviderNames.WITHOUT_JOB)
    public Object[] withoutJob() throws IOException {

        return ModelsBuilder.getUserBodies(DataProviderNames.WITHOUT_JOB);
    }
}
