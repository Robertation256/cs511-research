namespace java org.cs511.thrift

struct Windows {
    1: string processor;
    2: string memory;
    3: string graphics;
    4: string os;
}

struct Minimum {
    1: Windows win;
}

struct Requirements{
    1: Minimum min;
}

struct Desc {
    1: string sort,
    2: string desc,
}

struct ComplexThrift {
  1: string date,
  2: string developer,
  3: string publisher,
  4: Desc d,
  5: Requirements r,
}
