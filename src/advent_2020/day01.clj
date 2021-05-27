;; author: Mark W. Naylor
;; file:  day01.clj
;; date:  2021-Apr-09
(ns advent-2020.day01)

(def input [1688 1463 1461 1842 1441 1838 1583 1891 1876 1551 1506
            2005 1989 1417 1784 1975 1428 1485 1597 1871 105 788
            1971 1892 1854 1466 1584 1565 1400 1640 1780 1774 360
            1421 1368 1771 1666 1707 1627 1449 1677 1504 1721 1994
            1959 1862 1768 1986 1904 1382 1969 1852 1917 1966 1742
            1371 1405 1995 1906 1694 1735 1422 1719 1978 1641 1761
            1567 1974 1495 1973 1958 1599 1770 1600 1465 1865 1479
            1687 1390 1802 2008 645 1435 1589 1949 1909 1526 1667
            1831 1864 1713 1718 1232 1868 1884 1825 1999 1590 1759
            1391 1757 323 1612 1637 1727 1783 1643 1442 1452 675
            1812 1604 1518 1894 1933 1801 1914 912 1576 1961 1970
            1446 1985 1988 1563 1826 1409 1503 1539 1832 1698 1990
            1689 1532 765 1546 1384 1519 1615 1556 1754 1983 1394
            1763 1823 1788 1407 1946 1751 1837 1680 1929 1814 1948
            1919 1953 55 1731 1516 1895 1795 1890 1881 1799 1536
            1396 1942 1798 1767 1745 1883 2004 1550 1916 1650 1749
            1991 1789 1740 1490 1873 1003 1699 1669 1781 2000 1728
            1877 1733 1588 1168 1828 1848 1963 1928 1920 1493 1968
            1564 1572])

(def sum (partial reduce +))

(defn is2020? [coll]
  (= 2020 (sum coll)))

(defn pairs
  "From given input sequence, create a set of pairs."
  [items]
  (for [i items j items] [i j]))

(defn triples
  "From given input sequence, create a set of triples."
  [items]
  (for [i items j items k items] [i j k]))

(defn solve
  [group-fn items]
  (->> (group-fn items)
       (map sort)
       (into #{})
       (filter is2020?)
       first
       (reduce *)))

(def solve-pairs (partial solve pairs))
(def solve-triples (partial solve triples))

;; Pairs solution:   970816
;; Triples solution: 96047280

;; ------------------------------------------------------------------------------
;; BSD 3-Clause License

;; Copyright © 2021, Mark W. Naylor
;; All rights reserved.

;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are met:

;; 1. Redistributions of source code must retain the above copyright notice, this
;;    list of conditions and the following disclaimer.

;; 2. Redistributions in binary form must reproduce the above copyright notice,
;;    this list of conditions and the following disclaimer in the documentation
;;    and/or other materials provided with the distribution.

;; 3. Neither the name of the copyright holder nor the names of its
;;    contributors may be used to endorse or promote products derived from
;;    this software without specific prior written permission.

;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
;; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
;; IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;; DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
;; FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
;; DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
;; SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
;; CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
;; OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;; OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
