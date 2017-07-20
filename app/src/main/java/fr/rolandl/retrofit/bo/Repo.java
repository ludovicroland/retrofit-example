package fr.rolandl.retrofit.bo;

import java.io.Serializable;

/**
 * @author Ludovic Roland
 * @since 2017.07.20
 */
public final class Repo
    implements Serializable
{

  private int id;

  private String name;

  private String html_url;

  @Override
  public String toString()
  {
    return "Repo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", html_url='" + html_url + '\'' +
        '}';
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getHtml_url()
  {
    return html_url;
  }

  public void setHtml_url(String html_url)
  {
    this.html_url = html_url;
  }
}
