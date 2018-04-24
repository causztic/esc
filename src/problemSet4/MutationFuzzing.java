package problemSet4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MutationFuzzing {
	// Implement a generalized fuzzer that will take a file, read each line of the file, 
	// randomly choose a mutation operator (swap, bit flip or trim) and produce a different file 
	// with the modified lines.  This means for each input file, the fuzzer will produce one output file, 
	// where each line is modified with a random mutation operator. 
	// Choose any programming language. Make your program modular so that more mutation operators can be added easily. 
	
	private List<String> lines;
	private File file = new File("");
	
	public MutationFuzzing(File file) throws FileNotFoundException, IOException{
		this.file = file;
		this.lines = new CopyOnWriteArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();

		    while (line != null) {
		    	lines.add(line);
		        line = br.readLine();
		    }
		}
	}
	
	public void mutateAndWriteFile(){
		for (int i = 0; i < lines.size(); i++){
			lines.set(i, MutationFactory.getRandomMutator().mutate(lines.get(i)));
		}
		File f = new File("mutated_" + file.getName());
		try {
			PrintWriter pw = new PrintWriter(f);
			for(String s : lines) pw.println(s);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		try {
			MutationFuzzing mf = new MutationFuzzing(new File("src/problemSet4/test.txt"));
			mf.mutateAndWriteFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
