#~~~~~~~~~~~TODO~~~~~~~~~~~
#DONE	1	Quadratic
#DONE	2	Prime
#DONE	3	Pi
#DONE	4	Tax
#DONE	5	IdPassword
#DONE	6	Score
#DONE	7	FileSort
#DONE	8	Frequency
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
	print "How many terms worth of precision do you want?: ";
	my $n = <STDIN>;
	chomp($n)
	&pi($n)
}
sub tax_helper {
	print "Please enter income: ";
	my $income = <STDIN>;
	print "Please enter marital status (single/married): ";
	my $status = <STDIN>;
	print "Please enter residency (I/O): ";
	my $residency = <STDIN>;
	chomp($income);
	chomp($status);
	chomp($residency);
	&tax($income, $status, $residency);
}
sub idpassword_helper {
	print "Please enter first name: ";
	my $fname = <STDIN>;
	print "Please enter last name: ";
	my $lname = <STDIN>;
	chomp($fname);
	chomp($lname);

	&idpassword($fname, $lname);
}
sub score_helper {
	&score;
}
sub filesort_helper {
	print "Please enter infile: ";
	my $infile = <STDIN>;
	print "Please enter outfile: ";
	my $outfile = <STDIN>;
	chomp($infile);
	chomp($outfile);

	#!!!!!NEXT TWO LINES ARE FOR TESTING ONLY
	$infile = "7.txt";
	$outfile = "7.out";
	&filesort($infile, $outfile);
}
sub frequency_helper {
	print "Please enter infile: ";
	my $infile = <STDIN>;
	chomp($infile);

	#!!!!!NEXT LINE is FOR TESTING ONLY
	$infile = "8.txt";
	&frequency($infile);
}
sub power_helper {
	print "Please enter base number: ";
	my $base = <STDIN>;
	print "Please enter power: ";
	my $power = <STDIN>;
	chomp($base);
	chomp($power);

	&power($base, $power);
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
sub pi {
	my $n = $_[0];
	my $correction = 1.0;
	my $i = 1;
	for ($i = 1; $i < $n; $i++) {
		if (0 == $i%2) { #evens add

			$correction = $correction + (1/(($i*2)+1));
		} else { #odds subtract
			$correction = $correction - (1/(($i*2)+1));
		}
	}
	my $approx_pi = 4.0 * $correction;
	print "Pi is approxomately $approx_pi (correction = $correction)";
}
sub tax {
	my $income = $_[0];
	my $status = $_[1];
	my $residency = $_[2];
	my $errorFlag = 0;
	my $taxRate = 0;
	
	#Determine base rate
	if ("MARRIED" eq $status or "married" eq $status) {
		if ($income < 50000) {
			$taxRate = 0.1
		} else {
			$taxRate = 0.15
		}
	} elsif ("SINGLE" eq $status or "single" eq $status) {
		if ($income < 30000) {
			$taxRate = 0.2
		} else {
			$taxRate = 0.25
		}
	} else {
		$errorFlag = 1;
	}
	#modify for residency 
	if($errorFlag){
		#pass
	} elsif ("O" eq $residency or "o" eq $residency) {
		$taxRate = $taxRate - 0.03
	} elsif ("I" eq $residency or "i" eq $residency) {
		#pass
	} else {
		$errorFlag = 1;
	}

	#if in error, notify the user
	if ($errorFlag) {
		print "One or more of your parameters is incorrect";
	} else {
		my $tax = $income * $taxRate;
		print "Taxes due: $tax";
	}
}
sub idpassword {
	my $fname = uc($_[0]);
	my $lname = uc($_[1]);
	
	my $username = substr($fname, 0, 1).$lname;
	my $password = substr($fname, 0, 1).substr($fname, -1, 1).substr($lname, 0, 3).length($fname).length($lname);

	print "Username: $username\n";
	print "Password: $password\n";
}
sub score {
	my $thisLine;
	my $thisName;
	my $thisScore;
	my $minName;
	my $minScore;
	my $maxName;
	my $maxScore;
	my $runningTotal = 0;
	my $numScores = 0;

	$message = "Enter name & score separated by a space, or 'q' to quit: ";
	print "$message";
	$thisLine = <STDIN>;
	chomp($thisLine);


	while($thisLine ne "q" and $thisLine ne "Q"){
		my @this_arr = split(/ /, $thisLine);
		$thisName = @this_arr[0];
		$thisScore = @this_arr[1];
		if (!defined($minScore) or $thisScore < $minScore) {
			$minScore = $thisScore;
			$minName = $thisName;
		}
		if (!defined($maxScore) or $thisScore > $maxScore) {
			$maxScore = $thisScore;
			$maxName = $thisName;
		}

		$runningTotal = $runningTotal + $thisScore;
		$numScores++;

		print "$message";
		$thisLine = <STDIN>;
		chomp($thisLine);
	}
	print "Highest score: $maxScore \t Held by: $maxName\n";
	print "Lowest  score: $minScore \t Held by: $minName\n";
	my $average = $runningTotal / $numScores;
	print "Average score: $average"
}
sub filesort {
	open(my $ifh, "<", $_[0]);
	my @allLines = <$ifh>;
	my @sortedLines = sort(@allLines);

	open(my $ofh, ">", $_[1]);
	foreach my $x (@sortedLines) {
		print $ofh "$x";
	}
	close($ifh);
	close($ofh);
}
sub frequency {
	open(my $ifh, "<", $_[0]);
	my %lnames = ();
	my @allLines = <$ifh>;
	foreach my $x (@allLines) {
		my @this_arr = split(/ /, $x);
		my $lname = @this_arr[0];
		if(!defined($lnames{$lname})){
			$lnames{$lname} = 1;
		} else {
			$lnames{$lname} = $lnames{$lname} + 1;
		}
	}
	while(my ($name, $occurances) = each(%lnames)){
		print "$name occurs $occurances times in the file\n"
	}
}
sub power {
	my $total = 1;
	for (my $i = 0; $i < $_[1]; $i++) {
		$total = $total * $_[0];
	}
	print "total is $total";
}

#now that everything is ready
#lets get this party started
&main