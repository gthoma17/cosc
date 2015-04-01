#~~~~~~~~~~~TODO~~~~~~~~~~~
#DONE	1	Quadratic
#DONE	2	Prime
#	3	Pi
#	4	Tax
#	5	IdPassword
#	6	Score
#	7	FileSort
#	8	Frequency
#	9	Power
#	10	Factorial
#	11	Sort
#	12	MinMax
#	13	Positives/Factorials
#	14	EmuIds/EmuWeb
#	15	Whitespace/Areacode
#DONE	16	Menu


sub main{
	my $menu = 
	"\nPlease choose a selection:
	1	Quadratic
	2	Prime
	3	Pi
	4	Tax	
	5	IdPassword
	6	Score
	7	FileSort
	8	Frequency
	9	Power
	10	Factorial
	11	Sort
	12	MinMax
	13	Positives/Factorials
	14	EmuIds/EmuWeb
	15	Whitespace/Areacode
	16	Quit
	";
	print $menu;
	my $choice = <STDIN>;
	chomp($choice);
	while($choice != 16){
		#do stuff with this selection
		#if selection is valid call the helper function
		#--The helper will perform all nessesary info gathering
		#--.....or nothing is no additional info is needed
		if (1 == $choice) {
			&quadratic_helper
		} elsif (2 == $choice) {
			&prime_helper
		} elsif (3 == $choice) {
			&pi_helper
		} elsif (4 == $choice) {
			&tax_helper
		} elsif (5 == $choice) {
			&idpassword_helper
		} elsif (6 == $choice) {
			&score_helper
		} elsif (7 == $choice) {
			&filesort_helper
		} elsif (8 == $choice) {
			&frequency_helper
		} elsif (9 == $choice) {
			&power_helper
		} elsif (10 == $choice) {
			&factorial_helper
		} elsif (11 == $choice) {
			&sort_helper
		} elsif (12 == $choice) {
			&minmax_helper
		} elsif (13 == $choice) {
			&positives_fractionals_helper
		} elsif (14 == $choice) {
			&emuid_emuweb_helper
		} elsif (15 == $choice) {
			&whitespace_areacode_helper
		} else {
			print "Invalid selection"
		}
	
	
		#get the next selection
		print $menu;
		$choice = <STDIN>;
		chomp($choice);
	}
}

sub quadratic_helper {
	#get parameters
	print "Please enter A: ";
	my $A = <STDIN>;
	print "Please enter B: ";
	my $B = <STDIN>;
	print "Please enter C: ";
	my $C = <STDIN>;
	chomp($A);
	chomp($B);
	chomp($C);

	&quadratic($A,$B,$C)
}
sub prime_helper {
	print "Please enter a number: ";
	my $num = <STDIN>;
	chomp($num)
	&prime($num)
}
sub pi_helper {
	# body...
}
sub tax_helper {
	# body...
}
sub idpassword_helper {
	# body...
}
sub score_helper {
	# body...
}
sub filesort_helper {
	# body...
}
sub frequency_helper {
	# body...
}
sub power_helper {
	# body...
}
sub factorial_helper {
	# body...
}
sub sort_helper {
	# body...
}
sub minmax_helper {
	# body...
}
sub positives_fractionals_helper {
	# body...
}
sub emuid_emuweb_helper {
	# body...
}
sub whitespace_areacode_helper {
	# body...
}

sub quadratic {
	my $A = $_[0];
	my $B = $_[1];
	my $C = $_[2];

	if ((($B*$B)-(4*$A*$C)) >= 0) {
		print "Solution 1: ";
		print (($B * -1) + sqrt((($B*$B)-(4*$A*$C)))) / (2*$A);
		print "Solution 2: ";
		print (($B * -1) - sqrt((($B*$B)-(4*$A*$C)))) / (2*$A);
	} else {
		print "No solutions exist...";
	}
}
sub prime {
	my $num = $_[0];
	my $isPrime = 1;
	for (my $i = 2; $i < $num; $i++) {
		if (!($num % $i)) { #if no remainder, number isn't prime
			$isPrime = 0;
			last;
		}
	}
	if ($isPrime) {
		print "$num is prime"
	} else{
		print "$num is not prime"
	}
}


#now that everything is ready
#lets get this party started
&main