import glob
from sys import argv

import cv2
import numpy as np
from sklearn.cluster import KMeans
import check_image_kmean

# usage: python3 recoimages2018.py k1 k2 verbose
# ATTENTION: les noms de fichiers ne doivent comporter ni - ni espace

# sur ligne de commande: les 2 parametres de k means puis un param de verbose
k1 = int(argv[1])
k2 = int(argv[2])

if argv[3] == "True":
	verbose = True
else:
	verbose = False

listImages = glob.glob("*.jpg")

descriptors = np.empty(shape=(0, 128), dtype=float)  # array of all descriptors from all images
dimImage = []  # nb of descriptors per file

for s in listImages:
	if verbose:
		print("###", s, "###")
	image = cv2.imread(s)
	gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
	sift = cv2.xfeatures2d.SIFT_create()
	keypoints, descriptor = sift.detectAndCompute(gray, None)
	image = cv2.drawKeypoints(gray, keypoints, image, flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)
	cv2.imwrite("res/reco_" + s, image)
	dimImage.append(descriptor.shape[0])
	descriptors = np.append(descriptors, descriptor, axis=0)

# BOW initialization
bows = np.empty(shape=(0, k1), dtype=float)

# everything ready for the 1st k-means
kmeans1 = KMeans(n_clusters=k1, random_state=0).fit(descriptors)
if verbose:
	print("result of kmeans 1", kmeans1.labels_)

# writing the BOWs for second k-means
i = 0
for nb in dimImage:  # for each image
	tmpBow = [0] * k1
	j = 0
	while j < nb:  # for each descriptor of this image
		tmpBow[kmeans1.labels_[i]] += 1
		j += 1
		i += 1
	copyBow = tmpBow.copy()
	bows = np.append(bows, [copyBow], 0)

# a = 0
# for b in range(0, len(bows)):
# 	a = a + bows[b]
#
# for c in range(0, len(bows)):
# 	bows[c] = bows[c] / a

if verbose:
	print("nb of descriptors per file : ", dimImage)
	print("BOWs : ", bows)

# ready for second k-means
kmeans2 = KMeans(n_clusters=k2, random_state=0).fit(bows)
if verbose:
	print("result of kmeans 2", kmeans2.labels_)

check_image_kmean.check(kmeans1, kmeans2)
