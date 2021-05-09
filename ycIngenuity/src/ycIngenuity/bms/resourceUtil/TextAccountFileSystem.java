package ycIngenuity.bms.resourceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextAccountFileSystem implements ResourceSLU<Account> {
	private Path path;
	private Charset charset = StandardCharsets.UTF_8;
	private String column = "email,"
			+ "pw";
	public TextAccountFileSystem(Path path) {
		this.path = path;
	}
	
	@Override
	public List<Account> load_resource() {
		List<Account> resources = new ArrayList<>();
		String line;
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(path, charset);
			bufferedReader.readLine();
			while((line = bufferedReader.readLine()) != null) {
				resources.add(parseAccount(line));
			}
		}catch(IOException e) {
			System.out.println("TextResource-Account Failed to read file : " + e.getMessage());
		}
		return resources;
	}

	@Override
	public Boolean save_resource(List<Account> resources) {
		String newLine = System.lineSeparator();
		StringBuilder csvStr = new StringBuilder();
		csvStr.append(this.column + newLine);
		for(Account item : resources) {
			csvStr.append(item.toCSV() + newLine);
		}
		Boolean success = writeFile(csvStr.toString());
		return success;
	}

	@Override
	public Boolean update_resource(List<Account> resources) {
		// TODO Auto-generated method stub
		return null;
	}

	private Account parseAccount(String file){
		String[] tokens = file.split(",");
		Account account = new Account(tokens);
		return account;
	}
	
	private Boolean writeFile(String content) {
		Boolean success = false;
		try {
			Files.write(path, content.getBytes(this.charset));
			success = true;
		}
		catch(IOException e) {
			System.out.println("TextResource-Account failed to write file : " + e.getMessage());
			e.printStackTrace();
		}
		return success;
	}


}
