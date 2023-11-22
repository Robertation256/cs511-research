// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: single_proto.proto

package org.cs511.proto;

public final class SingleProto {
  private SingleProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface single_protoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:single_proto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string tconst = 1;</code>
     * @return The tconst.
     */
    java.lang.String getTconst();
    /**
     * <code>string tconst = 1;</code>
     * @return The bytes for tconst.
     */
    com.google.protobuf.ByteString
        getTconstBytes();

    /**
     * <code>string rating = 2;</code>
     * @return The rating.
     */
    java.lang.String getRating();
    /**
     * <code>string rating = 2;</code>
     * @return The bytes for rating.
     */
    com.google.protobuf.ByteString
        getRatingBytes();
  }
  /**
   * Protobuf type {@code single_proto}
   */
  public static final class single_proto extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:single_proto)
      single_protoOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use single_proto.newBuilder() to construct.
    private single_proto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private single_proto() {
      tconst_ = "";
      rating_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new single_proto();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private single_proto(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              tconst_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              rating_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.cs511.proto.SingleProto.internal_static_single_proto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.cs511.proto.SingleProto.internal_static_single_proto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.cs511.proto.SingleProto.single_proto.class, org.cs511.proto.SingleProto.single_proto.Builder.class);
    }

    public static final int TCONST_FIELD_NUMBER = 1;
    private volatile java.lang.Object tconst_;
    /**
     * <code>string tconst = 1;</code>
     * @return The tconst.
     */
    @java.lang.Override
    public java.lang.String getTconst() {
      java.lang.Object ref = tconst_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tconst_ = s;
        return s;
      }
    }
    /**
     * <code>string tconst = 1;</code>
     * @return The bytes for tconst.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getTconstBytes() {
      java.lang.Object ref = tconst_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tconst_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int RATING_FIELD_NUMBER = 2;
    private volatile java.lang.Object rating_;
    /**
     * <code>string rating = 2;</code>
     * @return The rating.
     */
    @java.lang.Override
    public java.lang.String getRating() {
      java.lang.Object ref = rating_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        rating_ = s;
        return s;
      }
    }
    /**
     * <code>string rating = 2;</code>
     * @return The bytes for rating.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getRatingBytes() {
      java.lang.Object ref = rating_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        rating_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getTconstBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tconst_);
      }
      if (!getRatingBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, rating_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getTconstBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, tconst_);
      }
      if (!getRatingBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, rating_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof org.cs511.proto.SingleProto.single_proto)) {
        return super.equals(obj);
      }
      org.cs511.proto.SingleProto.single_proto other = (org.cs511.proto.SingleProto.single_proto) obj;

      if (!getTconst()
          .equals(other.getTconst())) return false;
      if (!getRating()
          .equals(other.getRating())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TCONST_FIELD_NUMBER;
      hash = (53 * hash) + getTconst().hashCode();
      hash = (37 * hash) + RATING_FIELD_NUMBER;
      hash = (53 * hash) + getRating().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.cs511.proto.SingleProto.single_proto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.cs511.proto.SingleProto.single_proto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.cs511.proto.SingleProto.single_proto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(org.cs511.proto.SingleProto.single_proto prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code single_proto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:single_proto)
        org.cs511.proto.SingleProto.single_protoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.cs511.proto.SingleProto.internal_static_single_proto_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.cs511.proto.SingleProto.internal_static_single_proto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.cs511.proto.SingleProto.single_proto.class, org.cs511.proto.SingleProto.single_proto.Builder.class);
      }

      // Construct using org.cs511.proto.SingleProto.single_proto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        tconst_ = "";

        rating_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.cs511.proto.SingleProto.internal_static_single_proto_descriptor;
      }

      @java.lang.Override
      public org.cs511.proto.SingleProto.single_proto getDefaultInstanceForType() {
        return org.cs511.proto.SingleProto.single_proto.getDefaultInstance();
      }

      @java.lang.Override
      public org.cs511.proto.SingleProto.single_proto build() {
        org.cs511.proto.SingleProto.single_proto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.cs511.proto.SingleProto.single_proto buildPartial() {
        org.cs511.proto.SingleProto.single_proto result = new org.cs511.proto.SingleProto.single_proto(this);
        result.tconst_ = tconst_;
        result.rating_ = rating_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.cs511.proto.SingleProto.single_proto) {
          return mergeFrom((org.cs511.proto.SingleProto.single_proto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.cs511.proto.SingleProto.single_proto other) {
        if (other == org.cs511.proto.SingleProto.single_proto.getDefaultInstance()) return this;
        if (!other.getTconst().isEmpty()) {
          tconst_ = other.tconst_;
          onChanged();
        }
        if (!other.getRating().isEmpty()) {
          rating_ = other.rating_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.cs511.proto.SingleProto.single_proto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.cs511.proto.SingleProto.single_proto) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object tconst_ = "";
      /**
       * <code>string tconst = 1;</code>
       * @return The tconst.
       */
      public java.lang.String getTconst() {
        java.lang.Object ref = tconst_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          tconst_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string tconst = 1;</code>
       * @return The bytes for tconst.
       */
      public com.google.protobuf.ByteString
          getTconstBytes() {
        java.lang.Object ref = tconst_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          tconst_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string tconst = 1;</code>
       * @param value The tconst to set.
       * @return This builder for chaining.
       */
      public Builder setTconst(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        tconst_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string tconst = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearTconst() {
        
        tconst_ = getDefaultInstance().getTconst();
        onChanged();
        return this;
      }
      /**
       * <code>string tconst = 1;</code>
       * @param value The bytes for tconst to set.
       * @return This builder for chaining.
       */
      public Builder setTconstBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        tconst_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object rating_ = "";
      /**
       * <code>string rating = 2;</code>
       * @return The rating.
       */
      public java.lang.String getRating() {
        java.lang.Object ref = rating_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          rating_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string rating = 2;</code>
       * @return The bytes for rating.
       */
      public com.google.protobuf.ByteString
          getRatingBytes() {
        java.lang.Object ref = rating_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          rating_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string rating = 2;</code>
       * @param value The rating to set.
       * @return This builder for chaining.
       */
      public Builder setRating(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        rating_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string rating = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearRating() {
        
        rating_ = getDefaultInstance().getRating();
        onChanged();
        return this;
      }
      /**
       * <code>string rating = 2;</code>
       * @param value The bytes for rating to set.
       * @return This builder for chaining.
       */
      public Builder setRatingBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        rating_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:single_proto)
    }

    // @@protoc_insertion_point(class_scope:single_proto)
    private static final org.cs511.proto.SingleProto.single_proto DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.cs511.proto.SingleProto.single_proto();
    }

    public static org.cs511.proto.SingleProto.single_proto getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<single_proto>
        PARSER = new com.google.protobuf.AbstractParser<single_proto>() {
      @java.lang.Override
      public single_proto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new single_proto(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<single_proto> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<single_proto> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.cs511.proto.SingleProto.single_proto getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_single_proto_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_single_proto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022single_proto.proto\".\n\014single_proto\022\016\n\006" +
      "tconst\030\001 \001(\t\022\016\n\006rating\030\002 \001(\tB\021\n\017org.cs51" +
      "1.protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_single_proto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_single_proto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_single_proto_descriptor,
        new java.lang.String[] { "Tconst", "Rating", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}