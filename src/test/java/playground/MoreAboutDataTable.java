package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MoreAboutDataTable {

    @Test(dataProvider = "testWithObject" )
    public void someTesting(String name,Integer expectedLength) {
        Assert.assertEquals(name.length(),expectedLength,"Name length should match");
    }

    @DataProvider(name = "testWithObject")
    public Object[][] testDataWithObject(){
        Object[][] data = {
                {"Mor",3},
                {"Steve",5},
                {"Nooya",5}
        };
        return data;
    }


    @DataProvider(name = "testWithCustomPOJO")
    public Person[] testWithCustomPOJO() {
        Person[] data = {
                new Person("Mori", 4),
                new Person("Mo", 2)
        };
        return data;
    }


    @Test(dataProvider = "testWithCustomPOJO")
    public void someTestingCustomObject(Person person) {
        Assert.assertEquals(person.getName().length(), person.getLength(),"Name length should match");
    }

}
