/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.cs511.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class DummyAvroMediumFullDesc extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4951563572471380118L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"DummyAvroMediumFullDesc\",\"namespace\":\"org.cs511.avro\",\"fields\":[{\"name\":\"sort\",\"type\":\"string\"},{\"name\":\"desc\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<DummyAvroMediumFullDesc> ENCODER =
      new BinaryMessageEncoder<DummyAvroMediumFullDesc>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<DummyAvroMediumFullDesc> DECODER =
      new BinaryMessageDecoder<DummyAvroMediumFullDesc>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<DummyAvroMediumFullDesc> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<DummyAvroMediumFullDesc> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<DummyAvroMediumFullDesc> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<DummyAvroMediumFullDesc>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this DummyAvroMediumFullDesc to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a DummyAvroMediumFullDesc from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a DummyAvroMediumFullDesc instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static DummyAvroMediumFullDesc fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence sort;
  @Deprecated public java.lang.CharSequence desc;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public DummyAvroMediumFullDesc() {}

  /**
   * All-args constructor.
   * @param sort The new value for sort
   * @param desc The new value for desc
   */
  public DummyAvroMediumFullDesc(java.lang.CharSequence sort, java.lang.CharSequence desc) {
    this.sort = sort;
    this.desc = desc;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sort;
    case 1: return desc;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sort = (java.lang.CharSequence)value$; break;
    case 1: desc = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'sort' field.
   * @return The value of the 'sort' field.
   */
  public java.lang.CharSequence getSort() {
    return sort;
  }


  /**
   * Sets the value of the 'sort' field.
   * @param value the value to set.
   */
  public void setSort(java.lang.CharSequence value) {
    this.sort = value;
  }

  /**
   * Gets the value of the 'desc' field.
   * @return The value of the 'desc' field.
   */
  public java.lang.CharSequence getDesc() {
    return desc;
  }


  /**
   * Sets the value of the 'desc' field.
   * @param value the value to set.
   */
  public void setDesc(java.lang.CharSequence value) {
    this.desc = value;
  }

  /**
   * Creates a new DummyAvroMediumFullDesc RecordBuilder.
   * @return A new DummyAvroMediumFullDesc RecordBuilder
   */
  public static org.cs511.avro.DummyAvroMediumFullDesc.Builder newBuilder() {
    return new org.cs511.avro.DummyAvroMediumFullDesc.Builder();
  }

  /**
   * Creates a new DummyAvroMediumFullDesc RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new DummyAvroMediumFullDesc RecordBuilder
   */
  public static org.cs511.avro.DummyAvroMediumFullDesc.Builder newBuilder(org.cs511.avro.DummyAvroMediumFullDesc.Builder other) {
    if (other == null) {
      return new org.cs511.avro.DummyAvroMediumFullDesc.Builder();
    } else {
      return new org.cs511.avro.DummyAvroMediumFullDesc.Builder(other);
    }
  }

  /**
   * Creates a new DummyAvroMediumFullDesc RecordBuilder by copying an existing DummyAvroMediumFullDesc instance.
   * @param other The existing instance to copy.
   * @return A new DummyAvroMediumFullDesc RecordBuilder
   */
  public static org.cs511.avro.DummyAvroMediumFullDesc.Builder newBuilder(org.cs511.avro.DummyAvroMediumFullDesc other) {
    if (other == null) {
      return new org.cs511.avro.DummyAvroMediumFullDesc.Builder();
    } else {
      return new org.cs511.avro.DummyAvroMediumFullDesc.Builder(other);
    }
  }

  /**
   * RecordBuilder for DummyAvroMediumFullDesc instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<DummyAvroMediumFullDesc>
    implements org.apache.avro.data.RecordBuilder<DummyAvroMediumFullDesc> {

    private java.lang.CharSequence sort;
    private java.lang.CharSequence desc;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.cs511.avro.DummyAvroMediumFullDesc.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sort)) {
        this.sort = data().deepCopy(fields()[0].schema(), other.sort);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.desc)) {
        this.desc = data().deepCopy(fields()[1].schema(), other.desc);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing DummyAvroMediumFullDesc instance
     * @param other The existing instance to copy.
     */
    private Builder(org.cs511.avro.DummyAvroMediumFullDesc other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.sort)) {
        this.sort = data().deepCopy(fields()[0].schema(), other.sort);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.desc)) {
        this.desc = data().deepCopy(fields()[1].schema(), other.desc);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'sort' field.
      * @return The value.
      */
    public java.lang.CharSequence getSort() {
      return sort;
    }


    /**
      * Sets the value of the 'sort' field.
      * @param value The value of 'sort'.
      * @return This builder.
      */
    public org.cs511.avro.DummyAvroMediumFullDesc.Builder setSort(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.sort = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'sort' field has been set.
      * @return True if the 'sort' field has been set, false otherwise.
      */
    public boolean hasSort() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'sort' field.
      * @return This builder.
      */
    public org.cs511.avro.DummyAvroMediumFullDesc.Builder clearSort() {
      sort = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'desc' field.
      * @return The value.
      */
    public java.lang.CharSequence getDesc() {
      return desc;
    }


    /**
      * Sets the value of the 'desc' field.
      * @param value The value of 'desc'.
      * @return This builder.
      */
    public org.cs511.avro.DummyAvroMediumFullDesc.Builder setDesc(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.desc = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'desc' field has been set.
      * @return True if the 'desc' field has been set, false otherwise.
      */
    public boolean hasDesc() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'desc' field.
      * @return This builder.
      */
    public org.cs511.avro.DummyAvroMediumFullDesc.Builder clearDesc() {
      desc = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DummyAvroMediumFullDesc build() {
      try {
        DummyAvroMediumFullDesc record = new DummyAvroMediumFullDesc();
        record.sort = fieldSetFlags()[0] ? this.sort : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.desc = fieldSetFlags()[1] ? this.desc : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<DummyAvroMediumFullDesc>
    WRITER$ = (org.apache.avro.io.DatumWriter<DummyAvroMediumFullDesc>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<DummyAvroMediumFullDesc>
    READER$ = (org.apache.avro.io.DatumReader<DummyAvroMediumFullDesc>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.sort);

    out.writeString(this.desc);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.sort = in.readString(this.sort instanceof Utf8 ? (Utf8)this.sort : null);

      this.desc = in.readString(this.desc instanceof Utf8 ? (Utf8)this.desc : null);

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.sort = in.readString(this.sort instanceof Utf8 ? (Utf8)this.sort : null);
          break;

        case 1:
          this.desc = in.readString(this.desc instanceof Utf8 ? (Utf8)this.desc : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










