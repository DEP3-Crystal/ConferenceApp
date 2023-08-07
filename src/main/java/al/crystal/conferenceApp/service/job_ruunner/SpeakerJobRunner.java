package al.crystal.conferenceApp.service.job_ruunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Component
public class SpeakerJobRunner {

    @Autowired
    private TaskScheduler taskScheduler;

    public void scheduleTaskWithDelay(LocalDate localDate) {
        taskScheduler.schedule(new MyTask(), localDate.plusDays(2).atStartOfDay().toInstant(ZoneOffset.UTC));

    }

    class MyTask implements Runnable {

        @Override
        public void run() {
            ProcessBuilder build_test = new ProcessBuilder(
                    "cmd.exe", "/c", "java -cp src/main/resources/DE_Jar/confereceAppDe-0.0.1-SNAPSHOT.jar com.crystal.jobs.rating.TopSpeakerRate ");
            build_test.redirectErrorStream(true);
            Process p = null;
            try {
                p = build_test.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedReader output_reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String output = "";
            while (true) {
                try {
                    if ((output = output_reader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(output);
            }
        }
    }
}
