package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MoreAboutDataTableTest {

    @Test(dataProvider = "testWithObject" )
    public void someTesting(String name,Integer expectedLength) {
        Assert.assertEquals(name.length(),expectedLength,"Name length should match");
    }

    @DataProvider(name = "testWithObject")
    public Object[][] testDataWithObject(){
        return new Object[][]{
                {"Mor",3},
                {"Steve",5},
                {"Noo",5}
        };
    }


    @DataProvider(name = "testWithCustomPOJO")
    public Person[] testWithCustomPOJO() {
        return new Person[]{
                new Person("Mori", 4),
                new Person("Mo", 2)
        };
    }


    @Test(dataProvider = "testWithCustomPOJO")
    public void someTestingCustomObject(Person person) {
        Assert.assertEquals(person.getName().length(), person.getLength(),"Name length should match");
    }

}
