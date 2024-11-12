package org.example.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperties;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("TestAccount")
class CuentaTest {

    @BeforeAll
    static void initMethod() {
        System.out.println("Begin");
    }

    @AfterAll
    static void endMethod() {
        System.out.println("End");
    }

    @RepeatedTest(5)
    @DisplayName("Testing field name to upper")
    void testNameToUpper(){
        Account objAccount = new Account();
        objAccount.setPersonName("Favio");
        assertEquals(objAccount.getPersonName(), "FAVIO","The name was not converted to uppercase");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Manuel","Favio","Alonso","Logan","Ylene"})
    void testNameToUpper(String strName){
        Account objAccount = new Account();
        objAccount.setPersonName(strName);
        assertEquals(objAccount.getPersonName(), strName.toUpperCase(),"The name was not converted to uppercase");
    }

    @Test
    @DisplayName("Testing field name without space")
    void testNameWithoutSpace() {
        Account objAccount = new Account();
        objAccount.setPersonName(" ");
        String objResultado = objAccount.getPersonName();
        assertTrue(objResultado.isEmpty());
    }

    @Test
    void testNameNotNull() {
        Account objAccount = new Account();
        NullPointerException objException = Assertions.assertThrows(NullPointerException.class, () -> objAccount.setPersonName(null));
        assertEquals(objException.getMessage(), "Name cannot be null");
    }

    @Test
    void testAgePermit() {
        Account objAccount = new Account();
        objAccount.setAge(20);
        assertEquals(20,objAccount.getAge());
    }

    @Test
    @Disabled
    void testAgeMax() {
        Account objAccount = new Account();
        objAccount.setAge(100);
        assertEquals(100,objAccount.getAge());
    }

    @Test
    void testAgeInvalid() {
        Account objAccount = new Account();
        objAccount.setAge(15);
        assertNull(objAccount.getAge());
    }

    @Test
    void testValidAge() {
        Account objAccount1 = new Account();
        objAccount1.setAge(18);
        Account objAccount2 = new Account();
        objAccount2.setAge(100);

        assertAll(
                () -> assertEquals(18,objAccount1.getAge()),
                () -> assertEquals(100,objAccount2.getAge())
        );

    }

    @Test
    void systemProperties() {
        Properties objProperties = System.getProperties();
        objProperties.forEach((o, o2) -> System.out.println("Property "+ o + "value " + o2));
    }

    @Test
    @EnabledIfEnvironmentVariable(named="JAVA_HOME", matches = "jdk")
    void systemPropertiesIfEnviromentEnabled() {
        Map<String, String> getEnv = System.getenv();
        getEnv.forEach((s, s2) -> System.out.println());
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testWithJRE() {
        System.out.println("Test with jre 8");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testWithJRE15() {
        System.out.println("Test with jre 15");
    }

    @Test
    @Disabled
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void timeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);
    }
}