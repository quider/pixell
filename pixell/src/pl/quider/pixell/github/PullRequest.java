package pl.quider.pixell.github;

public class PullRequest {
	private String html_url;
	private String diff_url;
	private String patch_url;
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
	 * @return the diff_url
	 */
	public String getDiff_url() {
		return diff_url;
	}
	/**
	 * @param diff_url the diff_url to set
	 */
	public void setDiff_url(String diff_url) {
		this.diff_url = diff_url;
	}
	/**
	 * @return the patch_url
	 */
	public String getPatch_url() {
		return patch_url;
	}
	/**
	 * @param patch_url the patch_url to set
	 */
	public void setPatch_url(String patch_url) {
		this.patch_url = patch_url;
	}
}
