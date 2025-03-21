package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema; // Cambiado a la nueva biblioteca de Springdoc
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEnigmaRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class GetEnigmaRequest   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("enigma")
  private String enigma = null;

  public GetEnigmaRequest header(Header header) {
    this.header = header;
    return this;
  }

  /**
   * Get header
   * @return header
  **/
  @Schema(required = true, description = "")
  @NotNull

  @Valid
  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public GetEnigmaRequest enigma(String enigma) {
    this.enigma = enigma;
    return this;
  }

  /**
   * Get enigma
   * @return enigma
  **/
  @Schema(required = true, description = "")
  @NotNull

  public String getEnigma() {
    return enigma;
  }

  public void setEnigma(String enigma) {
    this.enigma = enigma;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEnigmaRequest getEnigmaRequest = (GetEnigmaRequest) o;
    return Objects.equals(this.header, getEnigmaRequest.header) &&
        Objects.equals(this.enigma, getEnigmaRequest.enigma);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, enigma);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEnigmaRequest {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    enigma: ").append(toIndentedString(enigma)).append("\n");
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
