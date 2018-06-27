package net.resetarcade.redmine.redmine;

import java.util.Collection;
import java.util.List;

import com.taskadapter.redmineapi.Params;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.CustomField;
import com.taskadapter.redmineapi.bean.Issue;

import net.resetarcade.redmine.Config.RedmineConfig;

public class SprintManager {

	private RedmineManager redmine = RedmineConfig.getRedmineManager();
	private String currentSprint;
	private String nextSprint;
	private String projectId;
	private String scrumMasterId;
	
	public SprintManager(String currentSprint, String nextSprint, String projectId,
			String scrumMasterId) {;
		this.currentSprint = currentSprint;
		this.nextSprint = nextSprint;
		this.projectId = projectId;
		this.scrumMasterId = scrumMasterId;
	}




	public void closeSprint() throws RedmineException {
		//assignUserOnIssuesWithoutAssignedFromCurrentSprint();
		//closeManagerTasksAssignedToScrumMasterFromCurrentSprint();
		//copyIssuesFromCurrentSprintToNextSprint();
		//closeAllTasksFromCurrentSprint();
		generateSprintEndSummary();
	}
	
	
	
	
	private List<Issue> getIssues(Params params) throws RedmineException{
		params
			.add("set_filter", "1")
			.add("project_id", projectId)
			.add("cf_3", currentSprint);
		
		return redmine.getIssueManager().getIssues(params).getResults();
	}
	
	public void assignUserOnIssuesWithoutAssignedFromCurrentSprint() throws RedmineException {
		List<Issue> issues = getIssues(new Params()
				.add("assigned_to_id", "!*"));
		
		for (Issue issue : issues) {
			issue.setAssigneeId(issue.getAuthorId());
			redmine.getIssueManager().update(issue);
		}

	}
	
	public void closeManagerTasksAssignedToScrumMasterFromCurrentSprint() throws RedmineException {
		List<Issue> issues = getIssues(new Params()
				.add("op[status_id]", "o")
				.add("tracker_id", "3")
				.add("assigned_to_id", scrumMasterId));
		
		for (Issue issue : issues) {
			issue.setStatusId(2);
			redmine.getIssueManager().update(issue);
			issue.setStatusId(5);
			redmine.getIssueManager().update(issue);			
		}	
		
	}
	
	public void copyIssuesFromCurrentSprintToNextSprint() throws RedmineException {
		List<Issue> issues = getIssues(new Params()
				.add("op[status_id]", "o"));
		
		for (Issue issue : issues) {
			Collection<CustomField> collections = issue.getCustomFields();
			for (CustomField customField : collections) {
				if(customField.getId() == 3) {
					customField.setValue(nextSprint);
				}
			}
			Issue newIssue = redmine.getIssueManager().createIssue(issue);
			redmine.getIssueManager().createRelation( newIssue.getId(), issue.getId(), "copied_from");
		}	
	}
	
	public void closeAllTasksFromCurrentSprint() throws RedmineException {
		List<Issue> issues = getIssues(new Params()
				.add("op[status_id]", "o"));
		
		for (Issue issue : issues) {
			issue.setStatusId(7);
			redmine.getIssueManager().update(issue);
		}
		
	}
	
	public Integer generateSprintEndSummary() throws RedmineException {
		List<Issue> issues = getIssues(new Params());
		
		for (Issue issue : issues) {
			
		}
		
		Issue issue = redmine.getIssueManager().getIssueById(12475);
		System.out.println(issue.getDescription());
		
		return null;
		
	}
	
	
}
