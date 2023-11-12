namespace java org.cs511.thrift

struct Desc {
    1: string sort,
    2: string desc,
}

struct SimpleThrift {
  1: string date,
  2: string developer,
  3: string publisher,
  4: Desc d,
}
