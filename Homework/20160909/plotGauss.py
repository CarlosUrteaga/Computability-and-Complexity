#hist.py
import matplotlib.mlab as mlab
import matplotlib.pyplot as plt
theta=[]
with open("file2.txt") as f:
    for line in f:
        theta.append(float(line))

#print theta

num_bins = 72
# the histogram of the data
n, bins, patches = plt.hist(theta, num_bins, range=[-6,6], normed = True,        histtype='bar',facecolor='green')
plt.xlabel(r'$\theta$($\degree$)')
plt.ylabel(r'$P(\theta)$')
plt.show()
