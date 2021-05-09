package ycIngenuity.bms.resourceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextRemoteLightFileSystem implements ResourceSLU<RemoteLight>{
	private Path path;
	private Charset charset = StandardCharsets.UTF_8;
	private String column = "device_id,"
			+ "connection_string,"
			+ "light,"
			+ "online,"
			+ "last_updated,"
			+ "installed_date,"
			+ "building,"
			+ "floor,"
			+ "room_name,"
			+ "room_code";
	
	public TextRemoteLightFileSystem(Path path) {
		this.path = path;
	}
	
	
	@Override
	public List<RemoteLight> load_resource() {
		List<RemoteLight> resources = new ArrayList<>();
		String line;
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(path, charset);
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				resources.add(parseRemoteLight(line));
			}
			
		}catch(IOException e) {
			System.out.println("TextResource-RemoteLight failed to read file : " + e.getMessage());
		}
		return resources;
	}
	
	
	@Override
	public Boolean save_resource(List<RemoteLight> resources) {
		String newLine = System.lineSeparator();
		StringBuilder csvStr = new StringBuilder();
		csvStr.append(this.column + newLine);
		for(RemoteLight item : resources) {
			csvStr.append(item.toCSV() + newLine);
		}
		Boolean success = writeFile(csvStr.toString());
		return success;
	}
	
	
	@Override
	public Boolean update_resource(List<RemoteLight> resources) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private RemoteLight parseRemoteLight(String file){
		String[] tokens = file.split(",");
		RemoteLight light = new RemoteLight(tokens);
		return light;
	}

	
	private Boolean writeFile(String content) {
		Boolean success = false;
		try {
			Files.write(this.path, content.getBytes(this.charset));
			success = true;
		} catch (IOException e) {
			System.out.println("TextResource-RemoteLight failed to write file : " + e.getMessage());
			e.printStackTrace();
		}
		return success;
	}


}
