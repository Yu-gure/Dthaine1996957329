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

## Learning Summary (Merge vs Rebase vs Squash vs Cherry-pick)

### Merge
A merge combines two branches by creating a merge commit (unless it fast-forwards). It preserves the true history of how work happened and is useful when you want a record that “branch X was merged into branch Y.” The downside is it can make the history look noisy if there are lots of small merges.

**When I’d use it:** when collaborating with others, when I want to preserve branch context, or when merging long-running work where keeping the full history matters.

### Rebase
A rebase “replays” commits from one branch onto another base commit, making the history look linear. This is great for keeping `dev` readable, but it can require conflict resolution and you have to be careful rebasing shared branches (because it rewrites commit hashes).

**When I’d use it:** to update my feature branch with the latest `dev` before merging, and to keep history clean for a project that prefers linear logs.

### Squash (interactive rebase squash)
Squashing combines multiple commits into one commit. This is useful when a feature branch has messy development commits (fixes, experiments, “oops” commits) and you want the final history to show a single clean feature commit.

**When I’d use it:** before merging a feature branch into `dev` so the main development branch stays readable and reviewable.

### Cherry-pick
Cherry-pick applies a single commit from one branch onto another without merging the whole branch. This is good for “hotfix” style changes where you only want one specific commit.

**When I’d use it:** applying an urgent fix directly to `main`, then merging `main` back into `dev` so both branches include the fix.

---

## What I observed in history (feature1 vs feature2 vs feature3)
- **feature1**: looked more straightforward and had fewer history issues.
- **feature2**: got messy due to rebases/conflicts and required careful conflict resolution. It showed how easy it is to accidentally leave conflict markers and how important `git status`, `git diff`, and rerunning tests are.
- **feature3**: started with multiple “development” commits, then was cleaned up by squashing into a single meaningful commit message. This made the `dev` branch log much easier to read.

Overall, `dev` should look linear and understandable, while feature branches can be messy during development as long as they are cleaned up before merging.



