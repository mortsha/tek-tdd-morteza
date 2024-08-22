package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Activity1Test {

    public String getFullName(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new RuntimeException("First name or last name can not be null");

        if (firstName.isEmpty() || lastName.isEmpty())
            throw new RuntimeException("First name and last name can not be Empty");

        firstName = firstName.trim();
        lastName = lastName.trim();

        return lastName.toUpperCase() + ", " + firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    @Test(dataProvider = "positiveTestData")
    public void positiveTesting(String firstName, String lastName, String expectedFullName) {
        String fullName = getFullName(firstName, lastName);

        Assert.assertEquals(fullName, expectedFullName, "FullName should match format");
    }


    @DataProvider(name = "positiveTestData")
    private String[][] positiveTestData() {
        return new String[][]{
                {"morteza", "Sharifi", "SHARIFI, Morteza"},
                {"steve", "bon", "BON, Steve"},
                {"john", "Dep", "DEP, John"},

        };
    }


    // Basic code
    @Test
    public void negativeTest() {
        try {
            getFullName(null, null);
            Assert.fail("Test supposed to throw Exception");
        } catch (RuntimeException exception) {
            Assert.assertTrue(true, "Catch the Exception Passing the Test");
        }
    }

    // good code

    @Test(expectedExceptions = RuntimeException.class)
    public void testNegativeWithExpectedException() {
        getFullName("", null);
    }

    // professional code
    @Test
    public void testNegativeWithAssertionThrow() {
        Assert.assertThrows(RuntimeException.class, () -> {
            getFullName("", "");
        });

    }

}
