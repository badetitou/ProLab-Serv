package com.tbe.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitHubManager {
	
	public static Map<String, String> getRepoInfos(String username, String repo) throws IOException{
		Map<String, String> infos = new HashMap<String, String>();
		GitHub github = GitHub.connectAnonymously();
		repo = repo.replaceAll(" ", "-");
		
		GHRepository ghr = github.getRepository(username+"/"+repo);
		
		infos.put("name", ghr.getFullName());
		infos.put("description", ghr.getDescription());
		infos.put("datecreation", ghr.getCreatedAt().toString());
		
		return infos;
	}
	
	public static boolean userExists(String login, String password) throws IOException{
		GitHub github = GitHub.connectUsingPassword(login, password);
		return github.isCredentialValid();
	}
	
	public static boolean createRepo(String login, String password, 
			String name, String punchline, String description) throws IOException{
			
		GitHub github = GitHub.connectUsingPassword(login, password);
		if(!userExists(login, password))
			return false;
		System.out.println("User " + login + " exists, creating repo...");
		GHRepository repo = github.createRepository(name, punchline, "", true);
		System.out.println("Repo created at " + repo.getGitTransportUrl() + ", init readme.md...");
		repo.createContent(description + " - Proudly powered by [ProLab](https://github.com/badetitou/ProLab-Serv)", "Init repo", "README.md");
		System.out.println("Github project ready.");
		return true;
	}
}
