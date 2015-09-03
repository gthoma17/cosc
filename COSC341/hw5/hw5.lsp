(defun flatten (L)
  (cond ((null L) nil)
  	((atom (car L)) (cons (car L) (flatten (cdr L))))
  	(T (append (flatten (car L)) (flatten (cdr L))))
  )
)
(defun contains (x L)
  (cond ((null L) nil)
        ((equal x (car L)) T)
        (T (contains x (cdr L)))
  )
)
(defun primep (n1 n2)
  (cond((= 1 n2) T)           ;if we made it n2 == 1, it's prime
       ((= 0 (rem n1 n2)) nil);if it's divisible by n2, it's not prime
       (T (primep n1 (- n2 1)))
  )
)
(defun f1 (L)
  (cond ((null L) 0)          ;empty lists contain no lists
        ((listp (car L)) (+ 1 (f1 (cdr L))))
                              ;if this is a list, add 1 to the number
                              ;of lists in the rest of the lists
        (T (f1 (cdr L)))      ;otherwise return the # in the rest of list
  )
)
(defun f2 (L)
  (cond ((null L) nil)        ;an empty list cant contain atom
        ((atom (car L)) T)    ;if car is an atom, L contains atom
        (T (f2 (cdr L)))      ;keep checking
  )
)
(defun f3 (L)
  (cond ((null L) nil)         ;empty lists don't have odd numbers
        ((= 1 (rem (car L) 2)) (append (list (car L)) (f3 (cdr L))))
                               ;if odd, append to list of others
        (T (f3 (cdr L)))       ;otherwise keep chugging
  )
)
(defun f4 (L)
  (car (f5 L))                 ;One could argue that this is a 'cheap' solution
)                              ;Another could argue I'm following D-R-Y methodology
(defun f5 (L)
  (cond ((null L) L)          ;empty lists can't be reversed
        (T (append (f5 (cdr L)) (list (car L))))
  )                           ;reverse the rest of the list, then append this
)
(defun f6 (L) (car (f7 L)))   ;One could argue that this is a 'cheap' solution
                              ;Another could argue I'm following D-R-Y methodology
(defun f7 (L)
  (cond ((null L) L)          ;null list contains no lists
        ((listp (car L)) (cons (car L) (f7 (cdr L))))
                              ;if this is a list return it with the others
        (T (f7 (cdr L)))      ;otherwise keep lookng
  )
)
(defun f8 (L) (apply '* (flatten L))) ;apply '*' to every integer in list
(defun f9 (L)
  (cond ((null L) nil)        ;empty lists contain no duplicates
        ((contains (car L) (cdr L)) (f9 (cdr L)))
                              ;if car is in cdr, skip it
        (T (append (list (car L)) (f9 (cdr L))))
                              ;otherwise return it with the others
  )
)
(defun f10 (L M)
  (cond ((null L) nil)        ;empty list. go away
        ((contains (car L) M) (append (list (car L)) (f10 (cdr L) M)))
                              ;if carl is in m, return it with the others
        (T (f10 (cdr L) M))   ;otherwise keep going
  )
)
(defun f11 (N)
  (primep N (- N 1))
)