package al.crystal.conferenceApp.service.job_ruunner;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Component
public class EventJobRunner {

    @Autowired
    private TaskScheduler taskScheduler;

    public void scheduleTaskWithDelay(LocalDateTime localDate){
        ScheduledFuture<?> schedule = taskScheduler.schedule(new MyTask(), Date.from(localDate.toDate().toInstant()));

    }

    class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println("running the joooooooooooooooobbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//            ProcessBuilder build_test = new ProcessBuilder(
//                    "cmd.exe", "/c", "java -cp src/main/resources/DE_Jar/confereceAppDe-0.0.1-SNAPSHOT.jar com.crystal.jobs.rating.TopSessionRate ");
//            build_test.redirectErrorStream(true);
//            Process p = null;
//            try {
//                p = build_test.start();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            BufferedReader output_reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String output = "";
//            while (true) {
//                try {
//                    if ((output = output_reader.readLine()) == null) break;
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(output);
//            }
        }
    }
}
