import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/***
 * Program's driver class. Responsible for creating the buffers used between each Filter class as well
 * as placing each Filter class into a ThreadExecutor
 *
 * @author Kyle Dorcey
 * @version 1.8
 */
public class DNAMain {
    public static void main (String args[]) throws InterruptedException{

        ExecutorService tailorGetItCauseTailorsExecuteThreadHaHa = Executors.newCachedThreadPool();

        Buffer filter1To2 = new FilterBuffer();
        Buffer filter2To3 = new FilterBuffer();
        Buffer filter3to4 = new FilterBuffer();
        Buffer filter4to5 = new FilterBuffer();

        tailorGetItCauseTailorsExecuteThreadHaHa.execute(new Filter1GapFinder(filter1To2, filter1To2));
        tailorGetItCauseTailorsExecuteThreadHaHa.execute(new Filter2ReverseComplementor(filter1To2, filter2To3));
        tailorGetItCauseTailorsExecuteThreadHaHa.execute(new Filter3FrameBuilder(filter2To3, filter3to4));
        tailorGetItCauseTailorsExecuteThreadHaHa.execute(new Filter4Translator(filter3to4, filter4to5));
        tailorGetItCauseTailorsExecuteThreadHaHa.execute(new Filter5ORFFinder(filter4to5, filter4to5));
        tailorGetItCauseTailorsExecuteThreadHaHa.shutdown();
        tailorGetItCauseTailorsExecuteThreadHaHa.awaitTermination(2, TimeUnit.MINUTES);

        Filter2ReverseComplementor.printDNA();
        Filter3FrameBuilder.print();
        Filter4Translator.printAminoAcids();
        Filter5ORFFinder.printRelevantORF();


    }
}
