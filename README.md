# efp-clojure-tracking-inventory

generated using Luminus version "2.9.12.62"

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To run locally create `dev-config.edn` with content:

```
{:dev true
 :port 3000
 :nrepl-port 7000
 :database-url "mongodb://..."
}
```

To start a web server for the application, run:

    lein run 