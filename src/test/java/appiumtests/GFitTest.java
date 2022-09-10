package appiumtests;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

//import io.appium.java_client.M


import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;

public class GFitTest {

    static AppiumDriver<MobileElement> driver;


    public static void main(String[] args) throws Exception {

            //Get Total Rows and Columns of Excel Data
            int totalDataRows = Utils.getTotalRows("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
            int totalDataCols = Utils.getTotalCols("/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");

            //BPDate (0th col), Systolic(1st col), Diastolic(2nd col), Weight (3rd col)
            openGFit();

            for (int i = 1; i <= totalDataRows; i++) {
                MobileElement entryBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/add_entry_fab"));
                entryBtn.click();
                String bpDate = Utils.getCellData(i, 0, "/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
                addBPDate(bpDate);
                String bpSystolic = Utils.getCellData(i, 1, "/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
                System.out.println("Systolic Value from excel row number " + i + " is " + bpSystolic);
                addBPSystolic(bpSystolic);
                System.out.println("Systolic Value of row number " + i + " saved successfully");
                String bpDiastolic = Utils.getCellData(i, 2, "/Users/architkamat/Desktop/ABHIJEET/GFitData.xlsx");
                System.out.println("Diastolic Value from excel row number " + i + " is " + bpDiastolic);
                addBPDiastolic(bpDiastolic);
                System.out.println("Diastolic Value of row number " + i + " saved successfully");
                saveBPData();
                System.out.println("Row number : " + i + " Saved successfully");
                if (i == 1) {
                    MobileElement allowSaveBtn = (MobileElement) driver.findElement(By.id("android:id/button1"));
                    allowSaveBtn.click();
                }
            }

        }

        public static void openGFit () throws Exception {

            DesiredCapabilities cap = new DesiredCapabilities();

            cap.setCapability("deviceName", "OnePlus 8");
            cap.setCapability("udid", "defef21e");
            cap.setCapability("platformName", "Android");
            cap.setCapability("platformVersion", "12");
            cap.setCapability("automationName", "UIAutomator2");
            cap.setCapability("appWaitDuration", 5000);
            cap.setCapability("appPackage", "com.google.android.apps.fitness");
            cap.setCapability("appActivity", "com.google.android.apps.fitness.shared.container.MainActivity");
            cap.setCapability("ignoreHiddenApiPolicyError", true);

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver(url, cap);
            System.out.println("Application Started....");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            MobileElement selUser = (MobileElement) driver.findElement(By.xpath("//android.widget.Spinner[@content-desc=\"Account: Madhuri Kamat kamat.madhuri@gmail.com\"]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ImageView"));
            selUser.click();

            MobileElement selectUser = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]"));
            selectUser.click();

            MobileElement contBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/login_button"));
            contBtn.click();

            MobileElement nextBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/next_button"));
            nextBtn.click();

            MobileElement skipBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/skip_button"));
            skipBtn.click();

        }

        public static void addBPDate (String bpDate) throws Exception
        {
            //Select & click Blood Pressure
            MobileElement addBP = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.ImageButton"));
            addBP.click();

            //Set today's date & time
            MobileElement clickDate = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/date_button"));
            clickDate.click();

            MobileElement editDateBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/mtrl_picker_header_toggle"));
            editDateBtn.click();

            MobileElement setDate = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText"));
            setDate.click();
            setDate.setValue(bpDate);

            MobileElement confirmDateBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/confirm_button"));
            confirmDateBtn.click();
        }


        public static void addBPSystolic (String bpSystolicExcel) throws Exception
        {
            MobileElement currentSystolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[1]/android.widget.EditText"));
            String curSystolic = currentSystolic.getText();
            int curSys = Integer.parseInt(curSystolic);
            int bpSysExcel = Integer.parseInt(bpSystolicExcel);
            int incrBy = curSys - bpSysExcel;

            MobileElement deCrSystolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[1]/android.widget.Button[1]"));
            MobileElement inCrSystolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[1]/android.widget.Button[2]"));

            if (incrBy < 0) {
                //Increment the Systolic - Current value is less than Systolic value of Excel Sheet
                for (int i = 0; i > incrBy; i--) {
                    inCrSystolic.click();
                }
            } else {
                //Decrement the Systolic - Current value is greater than Systolic value of Excel Sheet
                for (int i = 0; i < incrBy; i++) {
                    deCrSystolic.click();
                }
            }
        }

        public static void addBPDiastolic (String bpDiastolicExcel) throws Exception
        {
            MobileElement currentDiastolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[2]/android.widget.EditText"));
            String curDiastolic = currentDiastolic.getText();
            int curSys = Integer.parseInt(curDiastolic);
            int bpSysExcel = Integer.parseInt(bpDiastolicExcel);
            int incrBy = curSys - bpSysExcel;

            MobileElement deCrDiastolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[2]/android.widget.Button[1]"));
            MobileElement inCrDiastolic = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.NumberPicker[2]/android.widget.Button[2]"));

            if (incrBy < 0) {
                //Increment the Diastolic - Current value is less than Systolic value of Excel Sheet
                for (int i = 0; i > incrBy; i--) {
                    inCrDiastolic.click();
                }
            } else {
                //Decrement the Diastolic - Current value is greater than Systolic value of Excel Sheet
                for (int i = 0; i < incrBy; i++) {
                    deCrDiastolic.click();
                }
            }
        }


        public static void saveBPData()
        {
            MobileElement saveDataBtn = (MobileElement) driver.findElement(By.id("com.google.android.apps.fitness:id/container_action_button"));
            saveDataBtn.click();
        }
    }







