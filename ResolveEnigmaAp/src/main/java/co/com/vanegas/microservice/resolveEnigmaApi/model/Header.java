package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema; // Cambiado a la nueva biblioteca de Springdoc
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Header
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class Header   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("type")
  private String type = null;

  public Header id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @Schema(required = true, description = "")
  @NotNull

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Header type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @Schema(required = true, description = "")
  @NotNull

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Header header = (Header) o;
    return Objects.equals(this.id, header.id) &&
        Objects.equals(this.type, header.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Header {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
