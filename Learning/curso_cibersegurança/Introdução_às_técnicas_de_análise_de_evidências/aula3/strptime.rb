require "time"
puts Time.strptime("Oct 13 04:15:31 -0400", "%B %d %H:%M:%S %z").utc
puts Time.strptime("2026-10-13T09:15:31+01:00", "%Y-%m-%dT%H:%M:%S%z").utc

#2026-10-13 08:15:31 UTC
#2026-10-13 08:15:31 UTC