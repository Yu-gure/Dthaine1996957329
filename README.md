Branch Structure (Initial State)

At the start of this project, the repository contained several branches that were used for different purposes.

main
This is the stable production branch. Only completed and tested code is meant to be kept here.

dev
This branch is used as the main integration branch where features are combined and tested before being moved to main.

feature1
A feature branch that will be updated from dev using merge. This branch is used to practice resolving merge conflicts.

feature2
A feature branch that will be updated using rebase in order to keep the commit history linear.

feature3
A feature branch that contains multiple development commits which will later be squashed into a single commit before merging.

hotfix
This branch contains an urgent fix that is intended to be applied directly to the main branch.