if [ -d "../../scripts/" ]; then 
  scripts="../../scripts/"
else
  scripts="../../../scripts/"
fi
alias submit="sudo sh "$scripts"submit.sh"
alias make_template="sudo sh "$scripts"make_template.sh"
alias gcj_init="python ../../codejam-commandline-1.2-beta1/gcj_init_contest.py"
alias gcj_input="python ../../codejam-commandline-1.2-beta1/gcj_download_input.py"
alias gcj_all_inputs="gcj_input A large 0 && gcj_input A small 0 && gcj_input B small 0 && gcj_input B large 0 && gcj_input C small 0 && gcj_input C large 0 && gcj_input D small 0 && gcj_input D large 0 && gcj_input E small 0 && gcj_input E large 0"
alias gcj_submit="python ../../codejam-commandline-1.2-beta1/gcj_submit_solution.py"
