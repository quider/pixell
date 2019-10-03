package pl.quider.pixell.github;

public class Milestone {
	private String url;
	private String labels_url;
	private int id;
	private int number;
	private String title;
	private String description;
	private User creator;
	private int open_issues;
	private int closed_issues;
	private String state;
	private String created_at;
	private String updated_at;
	private String due_on;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	/**
	 * @return the open_issues
	 */
	public int getOpen_issues() {
		return open_issues;
	}
	/**
	 * @param open_issues the open_issues to set
	 */
	public void setOpen_issues(int open_issues) {
		this.open_issues = open_issues;
	}
	/**
	 * @return the closed_issues
	 */
	public int getClosed_issues() {
		return closed_issues;
	}
	/**
	 * @param closed_issues the closed_issues to set
	 */
	public void setClosed_issues(int closed_issues) {
		this.closed_issues = closed_issues;
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
	 * @return the due_on
	 */
	public String getDue_on() {
		return due_on;
	}
	/**
	 * @param due_on the due_on to set
	 */
	public void setDue_on(String due_on) {
		this.due_on = due_on;
	}
	
}
