## Instructions

- To convert to markdown - gmf
```bash
$ asciidoctor -b docbook weekly-report-example.adoc
$ pandoc -f docbook -t gfm weekly-report-example.xml -o weekly-report-example.md
```