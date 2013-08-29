package pl.quider.pixell.github;

import java.util.List;

public class Issue {
	private String url;
	private String labels_url;
	private String comments_url;
	private String events_url;
	private String html_url;
	private int id;
	private int number;
	private String title;
	private User user;
	private List<Label> labels;
	private String state;
	private User assignee;
	private Milestone milestone;
	private int comments;
	private String created_at;
	private String updated_at;
	private String closed_at;
	private PullRequest pull_request;
	private String body;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the labels_url
	 */
	public String getLabels_url() {
		return labels_url;
	}
	/**
	 * @param labels_url the labels_url to set
	 */
	public void setLabels_url(String labels_url) {
		this.labels_url = labels_url;
	}
	/**
	 * @return the comments_url
	 */
	public String getComments_url() {
		return comments_url;
	}
	/**
	 * @param comments_url the comments_url to set
	 */
	public void setComments_url(String comments_url) {
		this.comments_url = comments_url;
	}
	/**
	 * @return the events_url
	 */
	public String getEvents_url() {
		return events_url;
	}
	/**
	 * @param events_url the events_url to set
	 */
	public void setEvents_url(String events_url) {
		this.events_url = events_url;
	}
	/**
	 * @return the html_url
	 */
	public String getHtml_url() {
		return html_url;
	}
	/**
	 * @param html_url the html_url to set
	 */
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the labels
	 */
	public List<Label> getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the assignee
	 */
	public User getAssignee() {
		return assignee;
	}
	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	/**
	 * @return the milestone
	 */
	public Milestone getMilestone() {
		return milestone;
	}
	/**
	 * @param milestone the milestone to set
	 */
	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}
	/**
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(int comments) {
		this.comments = comments;
	}
	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	/**
	 * @return the updated_at
	 */
	public String getUpdated_at() {
		return updated_at;
	}
	/**
	 * @param updated_at the updated_at to set
	 */
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	/**
	 * @return the closed_at
	 */
	public String getClosed_at() {
		return closed_at;
	}
	/**
	 * @param closed_at the closed_at to set
	 */
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	/**
	 * @return the pull_request
	 */
	public PullRequest getPull_request() {
		return pull_request;
	}
	/**
	 * @param pull_request the pull_request to set
	 */
	public void setPull_request(PullRequest pull_request) {
		this.pull_request = pull_request;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	
}
