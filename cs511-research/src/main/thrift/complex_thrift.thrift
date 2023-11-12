namespace java org.cs511.thrift

Struct Windows {
    string processor = 1;
    string memory = 1;
    string graphics = 1;
    string os = 1;
}

Struct Minimum {
    Windows win = 1;
}

struct Requirements{
    Minimum min = 1;
}

struct Desc {
    1: string sort,
    2: string desc,
}

struct SimpleThrift {
  1: string date,
  2: string developer,
  3: string publisher,
  4: Desc d,
  5: Requirements r,
}
